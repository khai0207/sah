--v3
--[[
Sanji
0
0
siwangbaoxue
2
{default,qianjin,1,0,0,1,1.000000}{default,gongji4,1,0,2,58,1.000000}
1
{delay,-450.000000,0.000000,-450.000000,0.000000,1,1,0,45,0.000000,0,0,0,0}
0

0

0

daiji
]]--
local skillTest = {
attack_type       = "jingong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,1,1},{"gongji4",1.000000,2,58,1}}, 
pos_sequence      = {{-450.000000,0.000000,-450.000000,0.000000,1,1,0,45,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {}, 
bloodNum          = 7,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "siwangbaoxue",
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
