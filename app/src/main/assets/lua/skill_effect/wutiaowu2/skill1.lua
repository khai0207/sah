--v3
--[[
wutiaowu2
1
0
penxue
2
{default,qianjin,0,0,0,8,1.000000}{default,gongji1,1,0,9,41,1.000000}
2
{delay,0.000000,0.000000,0.000000,0.000000,0,0,0,0,0.000000,0,0,0,0}{default,0.000000,0.000000,-230.000000,0.000000,0,1,1,2,0.000000,0,0,0,0}
0

0

0

daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,8,0},{"gongji1",1.000000,9,41,1}}, 
pos_sequence      = {{0.000000,0.000000,0.000000,0.000000,0,0,0,0,0.000000,0,0,0,0},{0.000000,0.000000,-230.000000,0.000000,0,1,1,2,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {}, 
bloodNum          = 2,
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
