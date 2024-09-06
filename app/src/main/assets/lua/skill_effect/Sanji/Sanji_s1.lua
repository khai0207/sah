--v3
--[[
Sanji
0
0
penxue
3
{default,qianjin,1,0,0,1,1.000000}{default,gongji2,1,0,2,47,1.000000}{default,gongji9,1,0,48,65,1.000000}
1
{delay,-400.000000,0.000000,-400.000000,0.000000,1,1,0,45,0.000000,0,0,0,0}
0

0

0

daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,1,1},{"gongji2",1.000000,2,47,1},{"gongji9",1.000000,48,65,1}}, 
pos_sequence      = {{-400.000000,0.000000,-400.000000,0.000000,1,1,0,45,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {}, 
bloodNum          = 4,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
